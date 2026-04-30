import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from '../../services/api';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-estudiantes',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './estudiantes.html',
  styleUrl: './estudiantes.css'
})
export class Estudiantes implements OnInit {
  usuario: any;
  estudiantes: any[] = [];
  carreras: any[] = [];
  sangres: any[] = [];
  condicionesMedicas: any[] = [];
  alergias: any[] = [];
  discapacidades: any[] = [];
  estudianteSeleccionado: any = null;
  modoEdicion = false;

  // Filtros
  filtroCarrera: any = '';
  filtroInscrito: any = '';
  filtroPensum: any = '';

  // Formulario
  form: any = {
    carne: null, cui: '', nombres: '', apellidos: '',
    fechaNac: '', genero: '', fotografia: '', correoInstitucional: '',
    correoPersonal: '', direccion: '', anioIngreso: '',
    inscrito: false, pensumCerrado: false,
    sangre: null, carreras: []
  };

  constructor(private api: ApiService, private router: Router, private cdr: ChangeDetectorRef) {
    const sesion = sessionStorage.getItem('usuario');
    if (!sesion) this.router.navigate(['/login']);
    else this.usuario = JSON.parse(sesion);
  }

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    this.api.getAll('estudiantes').subscribe(data => {
      this.estudiantes = data;
      this.cdr.detectChanges();
    });
    this.api.getAll('carreras').subscribe(data => {
      this.carreras = data;
      this.cdr.detectChanges();
    });
    this.api.getAll('sangres').subscribe(data => {
      this.sangres = data;
      this.cdr.detectChanges();
    });
    this.api.getAll('condiciones-medicas').subscribe(data => {
      this.condicionesMedicas = data;
      this.cdr.detectChanges();
    });
    this.api.getAll('alergias').subscribe(data => {
      this.alergias = data;
      this.cdr.detectChanges();
    });
    this.api.getAll('discapacidades').subscribe(data => {
      this.discapacidades = data;
      this.cdr.detectChanges();
    });
  }

  filtrar() {
    let params = 'estudiantes?';
    if (this.filtroCarrera) params += `carrera=${this.filtroCarrera}&`;
    if (this.filtroInscrito !== '') params += `inscrito=${this.filtroInscrito}&`;
    if (this.filtroPensum !== '') params += `pensumCerrado=${this.filtroPensum}&`;
    this.api.getAll(params).subscribe(data => this.estudiantes = data);
  }

  limpiarFiltros() {
    this.filtroCarrera = '';
    this.filtroInscrito = '';
    this.filtroPensum = '';
    this.cargarDatos();
  }

  nuevo() {
    this.modoEdicion = false;
    this.form = {
      carne: null, cui: '', nombres: '', apellidos: '',
      fechaNac: '', genero: '', fotografia: '', correoInstitucional: '',
      correoPersonal: '', direccion: '', anioIngreso: '',
      inscrito: false, pensumCerrado: false,
      sangre: null, carreras: [],
      condicionesMedicas: [],
      alergias: [],
      discapacidades: []
    };
  }

  editar(e: any) {
    this.modoEdicion = true;
    this.form = {
      ...e,
      fechaNac: e.fechaNac ? e.fechaNac.substring(0, 10) : '',
      anioIngreso: e.anioIngreso ? e.anioIngreso.substring(0, 10) : '',
      sangre: e.sangre ? e.sangre.id : null,
      carreras: e.carreras ? e.carreras.map((c: any) => c.codigo) : []
    };
  }

  verDetalle(e: any) {
    this.estudianteSeleccionado = e;
  }

  guardar() {
    const body = {
      ...this.form,
      fechaNac: this.form.fechaNac ? this.form.fechaNac + 'T00:00:00' : null,
      anioIngreso: this.form.anioIngreso ? this.form.anioIngreso + 'T00:00:00' : null,
      sangre: this.form.sangre ? { id: this.form.sangre } : null,
      carreras: this.form.carreras.map((c: any) => ({ codigo: c })),
      condicionesMedicas: this.form.condicionesMedicas.map((id: number) => ({ id })),
      alergias: this.form.alergias.map((id: number) => ({ id })),
      discapacidades: this.form.discapacidades.map((id: number) => ({ id }))
    };

    if (this.modoEdicion) {
      this.api.put('estudiantes', this.form.carne, body).subscribe(() => {
        this.cargarDatos();
      });
    } else {
      this.api.post('estudiantes', body).subscribe(() => {
        this.cargarDatos();
      });
    }
  }

  cerrarSesion() {
  sessionStorage.removeItem('usuario');
  this.router.navigate(['/login']);
  }

  eliminar(carne: number) {
    if (confirm('¿Estás seguro de eliminar este estudiante?')) {
      this.api.delete('estudiantes', carne).subscribe(() => {
        this.cargarDatos();
      });
    }
  }

  toggleCarrera(codigo: number) {
    const idx = this.form.carreras.indexOf(codigo);
    if (idx === -1) this.form.carreras.push(codigo);
    else this.form.carreras.splice(idx, 1);
  }

    toggleCondicion(id: number) {
    const idx = this.form.condicionesMedicas.indexOf(id);
    if (idx === -1) this.form.condicionesMedicas.push(id);
    else this.form.condicionesMedicas.splice(idx, 1);
  }
  condicionSeleccionada(id: number): boolean {
    return this.form.condicionesMedicas.includes(id);
  }

  toggleAlergia(id: number) {
    const idx = this.form.alergias.indexOf(id);
    if (idx === -1) this.form.alergias.push(id);
    else this.form.alergias.splice(idx, 1);
  }
  alergiaSeleccionada(id: number): boolean {
    return this.form.alergias.includes(id);
  }

  toggleDiscapacidad(id: number) {
    const idx = this.form.discapacidades.indexOf(id);
    if (idx === -1) this.form.discapacidades.push(id);
    else this.form.discapacidades.splice(idx, 1);
  }
  discapacidadSeleccionada(id: number): boolean {
    return this.form.discapacidades.includes(id);
  }

  carreraSeleccionada(codigo: number): boolean {
    return this.form.carreras.includes(codigo);
  }
}