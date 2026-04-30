import { Component, OnInit } from '@angular/core';
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

  constructor(private api: ApiService, private router: Router) {
    const sesion = sessionStorage.getItem('usuario');
    if (!sesion) this.router.navigate(['/login']);
    else this.usuario = JSON.parse(sesion);
  }

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    this.api.getAll('estudiantes').subscribe(data => this.estudiantes = data);
    this.api.getAll('carreras').subscribe(data => this.carreras = data);
    this.api.getAll('sangres').subscribe(data => this.sangres = data);
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
      sangre: null, carreras: []
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
      sangre: this.form.sangre ? { id: this.form.sangre } : null,
      carreras: this.form.carreras.map((c: any) => ({ codigo: c }))
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

  carreraSeleccionada(codigo: number): boolean {
    return this.form.carreras.includes(codigo);
  }
}