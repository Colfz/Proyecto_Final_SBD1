import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../services/api';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-justificaciones',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './justificaciones.html',
  styleUrl: './justificaciones.css'
})
export class Justificaciones implements OnInit {
  usuario: any;
  justificaciones: any[] = [];
  estudiantes: any[] = [];
  motivos: any[] = [];
  estados: any[] = [];
  justificacionSeleccionada: any = null;

  form: any = {
    descripcion: '',
    documentoRespaldo: '',
    fechaPresentacion: '',
    estudiante: null,
    motivo: null,
    estado: null,
    fechasAusencia: ['']
  };

  constructor(
    private api: ApiService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
    const sesion = sessionStorage.getItem('usuario');
    if (!sesion) this.router.navigate(['/login']);
    else this.usuario = JSON.parse(sesion);
  }

  ngOnInit() {
    this.cargarDatos();
  }


  cargarDatos() {
    this.api.getAll('justificaciones').subscribe(data => {
      this.justificaciones = data;
      this.cdr.detectChanges();
    });
    this.api.getAll('estudiantes').subscribe(data => {
      this.estudiantes = data;
      this.cdr.detectChanges();
    });
    this.api.getAll('motivos').subscribe(data => {
      this.motivos = data;
      this.cdr.detectChanges();
    });
    this.api.getAll('estados').subscribe(data => {
      this.estados = data;
      this.cdr.detectChanges();
    });
  }

  nueva() {
    this.form = {
      descripcion: '',
      documentoRespaldo: '',
      fechaPresentacion: new Date().toISOString().substring(0, 10),
      estudiante: null,
      motivo: null,
      estado: null,
      fechasAusencia: ['']
    };
  }

  verDetalle(j: any) {
    this.justificacionSeleccionada = { ...j, fechasAusencia: [] };
    this.cdr.detectChanges();
    
    this.api.getAll(`fecha-ausencia/justificacion/${j.id}`).subscribe(data => {
      this.justificacionSeleccionada.fechasAusencia = data;
      this.cdr.detectChanges();
    });
  }

  agregarFecha() {
    this.form.fechasAusencia.push('');
  }

  eliminarFecha(i: number) {
    this.form.fechasAusencia.splice(i, 1);
  }

guardar() {
  const body = {
    descripcion: this.form.descripcion,
    documentoRespaldo: this.form.documentoRespaldo,
    fechaPresentacion: this.form.fechaPresentacion + 'T00:00:00',
    estudiante: { carne: this.form.estudiante },
    motivo: { id: this.form.motivo },
    estado: { id: 1 }
  };
  this.api.post('justificaciones', body).subscribe((j: any) => {
    this.form.fechasAusencia.forEach((fecha: string) => {
      if (fecha) {
        this.api.post('fecha-ausencia', {
          fechaAusencia: fecha + 'T00:00:00',
          justificacion: { id: j.id }
        }).subscribe();
      }
    });
    this.api.getAll('justificaciones').subscribe(data => {
      this.justificaciones = data;
      this.cdr.detectChanges();
    });
  });
}

actualizarEstado(id: number, estadoId: number) {
  const estado = this.estados.find(e => e.id == estadoId);
  if (!estado) return;

  this.api.put(`justificaciones/${id}`, 'estado', estado).subscribe({
    next: () => {
      
      const j = this.justificaciones.find(j => j.id === id);
      if (j) {
        j.estado = estado;
        this.cdr.detectChanges();
      }
    },
    error: (err) => {
      console.error('Error al actualizar estado', err);
      alert('Error al actualizar el estado.');
    }
  });
}

  eliminar(id: number) {
    if (confirm('¿Eliminar esta justificación?')) {
      this.api.delete('justificaciones', id).subscribe({
        next: () => {
          this.api.getAll('justificaciones').subscribe(data => {
            this.justificaciones = data;
            this.cdr.detectChanges();
          });
        },
        error: () => {
          alert('No se puede eliminar esta justificación.');
        }
      });
    }
  }

  cerrarSesion() {
    sessionStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }
}