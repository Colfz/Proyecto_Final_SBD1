import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../services/api';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-catalogos',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './catalogos.html',
  styleUrl: './catalogos.css'
})
export class Catalogos implements OnInit {
  usuario: any;

  // Datos
  motivos: any[] = [];
  tiposActividad: any[] = [];
  carreras: any[] = [];
  tiposDiscapacidad: any[] = [];
  condicionesMedicas: any[] = [];
  alergias: any[] = [];

  // Formularios
  nuevoMotivo = '';
  nuevoTipoActividad = '';
  nuevaCarrera: any = { codigo: null, nombre: '', coordinador: '' };
  nuevoTipoDiscapacidad = '';
  nuevaCondicion = '';
  nuevaAlergia = '';

  // Tab activo
  tabActivo = 'motivos';

  constructor(private api: ApiService, private router: Router, private cdr: ChangeDetectorRef) {
    const sesion = sessionStorage.getItem('usuario');
    if (!sesion) this.router.navigate(['/login']);
    else this.usuario = JSON.parse(sesion);
  }

  ngOnInit() { this.cargarDatos(); }

  cargarDatos() {
    this.api.getAll('motivos').subscribe(d => { this.motivos = d; this.cdr.detectChanges(); });
    this.api.getAll('tipos-actividad').subscribe(d => { this.tiposActividad = d; this.cdr.detectChanges(); });
    this.api.getAll('carreras').subscribe(d => { this.carreras = d; this.cdr.detectChanges(); });
    this.api.getAll('tipos-discapacidad').subscribe(d => { this.tiposDiscapacidad = d; this.cdr.detectChanges(); });
    this.api.getAll('condiciones-medicas').subscribe(d => { this.condicionesMedicas = d; this.cdr.detectChanges(); });
    this.api.getAll('alergias').subscribe(d => { this.alergias = d; this.cdr.detectChanges(); });
  }

  // Motivos
  agregarMotivo() {
    if (!this.nuevoMotivo.trim()) return;
    this.api.post('motivos', { nombre: this.nuevoMotivo }).subscribe(() => {
      this.nuevoMotivo = '';
      this.api.getAll('motivos').subscribe(d => {
        this.motivos = d;
        this.cdr.detectChanges();
      });
    });
  }

  eliminarMotivo(id: number) {
    if (confirm('¿Eliminar?')) {
      this.api.delete('motivos', id).subscribe({
        next: () => {
          this.api.getAll('motivos').subscribe(d => {
            this.motivos = d;
            this.cdr.detectChanges();
          });
        },
        error: () => {
          alert('No se puede eliminar porque está siendo usado en una justificación.');
        }
      });
    }
  }

  // Tipos de Actividad
  agregarTipoActividad() {
    if (!this.nuevoTipoActividad.trim()) return;
    this.api.post('tipos-actividad', { tipo: this.nuevoTipoActividad }).subscribe(() => {
      this.nuevoTipoActividad = '';
      this.api.getAll('tipos-actividad').subscribe(d => {
        this.tiposActividad = d;
        this.cdr.detectChanges();
      });
    });
  }

    eliminarTipoActividad(id: number) {
      if (confirm('¿Eliminar?')) {this.api.delete('tipos-actividad', id).subscribe({
        next: () => {
        this.api.getAll('tipos-actividad').subscribe(d => {
          this.tiposActividad = d;
          this.cdr.detectChanges();
        });
        },
          error: () => {
            alert('No se puede eliminar porque están estudiantes inscritos en esta actividad.');
          }
        });
    }
  }

  // Carreras
  agregarCarrera() {
    if (!this.nuevaCarrera.nombre.trim()) return;
    this.api.post('carreras', this.nuevaCarrera).subscribe(() => {
      this.nuevaCarrera = { codigo: null, nombre: '', coordinador: '' };
      this.api.getAll('carreras').subscribe(d => {
        this.carreras = d;
        this.cdr.detectChanges();
      });
    });
  }

  eliminarCarrera(codigo: number) {
        if (confirm('¿Eliminar?')) {this.api.delete('carreras', codigo).subscribe({
        next: () => {
        this.api.getAll('carreras').subscribe(d => {
          this.carreras= d;
          this.cdr.detectChanges();
        });
        },
          error: () => {
            alert('No se puede eliminar porque están estudiantes inscritos en esta Carrera.');
          }
        });
    }
  }

  // Tipos de Discapacidad
  agregarTipoDiscapacidad() {
    if (!this.nuevoTipoDiscapacidad.trim()) return;
    this.api.post('tipos-discapacidad', { nombre: this.nuevoTipoDiscapacidad }).subscribe(() => {
      this.nuevoTipoDiscapacidad = '';
      this.api.getAll('tipos-discapacidad').subscribe(d => {
        this.tiposDiscapacidad = d;
        this.cdr.detectChanges();
      });
    });
  }

  eliminarTipoDiscapacidad(id: number) {
        if (confirm('¿Eliminar?')) {this.api.delete('tipos-discapacidad', id).subscribe({
        next: () => {
        this.api.getAll('tipos-discapacidad').subscribe(d => {
          this.tiposDiscapacidad = d;
          this.cdr.detectChanges();
        });
        },
          error: () => {
            alert('No se puede eliminar porque hay estudiantes que presentan este tipo de discapacidad.');
          }
        });
    }
  }

  // Condiciones Médicas
  agregarCondicion() {
    if (!this.nuevaCondicion.trim()) return;
    this.api.post('condiciones-medicas', { nombre: this.nuevaCondicion }).subscribe(() => {
      this.nuevaCondicion = '';
      this.api.getAll('condiciones-medicas').subscribe(d => {
        this.condicionesMedicas = d;
        this.cdr.detectChanges();
      });
    });
  }

  eliminarCondicion(id: number) {
              if (confirm('¿Eliminar?')) {this.api.delete('condiciones-medicas', id).subscribe({
        next: () => {
        this.api.getAll('condiciones-medicas').subscribe(d => {
          this.condicionesMedicas = d;
          this.cdr.detectChanges();
        });
        },
          error: () => {
            alert('No se puede eliminar porque hay estudiantes que presentan este tipo de discapacidad.');
          }
        });
    }
  }

  // Alergias
  agregarAlergia() {
    if (!this.nuevaAlergia.trim()) return;
    this.api.post('alergias', { tipo: this.nuevaAlergia }).subscribe(() => {
      this.nuevaAlergia = '';
      this.api.getAll('alergias').subscribe(d => {
        this.alergias = d;
        this.cdr.detectChanges();
      });
    });
  }

  eliminarAlergia(id: number) {
        if (confirm('¿Eliminar?')) {this.api.delete('alergias', id).subscribe({
        next: () => {
        this.api.getAll('alergias').subscribe(d => {
          this.alergias = d;
          this.cdr.detectChanges();
        });
        },
          error: () => {
            alert('No se puede eliminar porque hay estudiantes que presentan este tipo de discapacidad.');
          }
        });
    }
  }

  cerrarSesion() {
    sessionStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }
}