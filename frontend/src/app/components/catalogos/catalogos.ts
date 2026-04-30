import { Component, OnInit } from '@angular/core';
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

  constructor(private api: ApiService, private router: Router) {
    const sesion = sessionStorage.getItem('usuario');
    if (!sesion) this.router.navigate(['/login']);
    else this.usuario = JSON.parse(sesion);
  }

  ngOnInit() { this.cargarDatos(); }

  cargarDatos() {
    this.api.getAll('motivos').subscribe(d => this.motivos = d);
    this.api.getAll('tipos-actividad').subscribe(d => this.tiposActividad = d);
    this.api.getAll('carreras').subscribe(d => this.carreras = d);
    this.api.getAll('tipos-discapacidad').subscribe(d => this.tiposDiscapacidad = d);
    this.api.getAll('condiciones-medicas').subscribe(d => this.condicionesMedicas = d);
    this.api.getAll('alergias').subscribe(d => this.alergias = d);
  }

  // Motivos
  agregarMotivo() {
    if (!this.nuevoMotivo.trim()) return;
    this.api.post('motivos', { nombre: this.nuevoMotivo }).subscribe(() => {
      this.nuevoMotivo = '';
      this.api.getAll('motivos').subscribe(d => this.motivos = d);
    });
  }
  eliminarMotivo(id: number) {
    if (confirm('¿Eliminar?')) this.api.delete('motivos', id).subscribe(() =>
      this.api.getAll('motivos').subscribe(d => this.motivos = d));
  }

  // Tipos de Actividad
  agregarTipoActividad() {
    if (!this.nuevoTipoActividad.trim()) return;
    this.api.post('tipos-actividad', { tipo: this.nuevoTipoActividad }).subscribe(() => {
      this.nuevoTipoActividad = '';
      this.api.getAll('tipos-actividad').subscribe(d => this.tiposActividad = d);
    });
  }
  eliminarTipoActividad(id: number) {
    if (confirm('¿Eliminar?')) this.api.delete('tipos-actividad', id).subscribe(() =>
      this.api.getAll('tipos-actividad').subscribe(d => this.tiposActividad = d));
  }

  // Carreras
  agregarCarrera() {
    if (!this.nuevaCarrera.nombre.trim()) return;
    this.api.post('carreras', this.nuevaCarrera).subscribe(() => {
      this.nuevaCarrera = { codigo: null, nombre: '', coordinador: '' };
      this.api.getAll('carreras').subscribe(d => this.carreras = d);
    });
  }
  eliminarCarrera(codigo: number) {
    if (confirm('¿Eliminar?')) this.api.delete('carreras', codigo).subscribe(() =>
      this.api.getAll('carreras').subscribe(d => this.carreras = d));
  }

  // Tipos de Discapacidad
  agregarTipoDiscapacidad() {
    if (!this.nuevoTipoDiscapacidad.trim()) return;
    this.api.post('tipos-discapacidad', { nombre: this.nuevoTipoDiscapacidad }).subscribe(() => {
      this.nuevoTipoDiscapacidad = '';
      this.api.getAll('tipos-discapacidad').subscribe(d => this.tiposDiscapacidad = d);
    });
  }
  eliminarTipoDiscapacidad(id: number) {
    if (confirm('¿Eliminar?')) this.api.delete('tipos-discapacidad', id).subscribe(() =>
      this.api.getAll('tipos-discapacidad').subscribe(d => this.tiposDiscapacidad = d));
  }

  // Condiciones Médicas
  agregarCondicion() {
    if (!this.nuevaCondicion.trim()) return;
    this.api.post('condiciones-medicas', { nombre: this.nuevaCondicion }).subscribe(() => {
      this.nuevaCondicion = '';
      this.api.getAll('condiciones-medicas').subscribe(d => this.condicionesMedicas = d);
    });
  }
  eliminarCondicion(id: number) {
    if (confirm('¿Eliminar?')) this.api.delete('condiciones-medicas', id).subscribe(() =>
      this.api.getAll('condiciones-medicas').subscribe(d => this.condicionesMedicas = d));
  }

  // Alergias
  agregarAlergia() {
    if (!this.nuevaAlergia.trim()) return;
    this.api.post('alergias', { tipo: this.nuevaAlergia }).subscribe(() => {
      this.nuevaAlergia = '';
      this.api.getAll('alergias').subscribe(d => this.alergias = d);
    });
  }
  eliminarAlergia(id: number) {
    if (confirm('¿Eliminar?')) this.api.delete('alergias', id).subscribe(() =>
      this.api.getAll('alergias').subscribe(d => this.alergias = d));
  }

  cerrarSesion() {
    sessionStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }
}