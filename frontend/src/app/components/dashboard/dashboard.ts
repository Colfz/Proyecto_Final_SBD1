import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {
  usuario: any;
  totalEstudiantes = 0;
  totalInscritos = 0;
  totalPendientes = 0;
  totalCarreras = 0;
  notificaciones: any[] = [];

  constructor(private api: ApiService, private router: Router) {
    const sesion = sessionStorage.getItem('usuario');
    if (!sesion) {
      this.router.navigate(['/login']);
    } else {
      this.usuario = JSON.parse(sesion);
    }
  }

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    this.api.getAll('estudiantes').subscribe(data => {
      this.totalEstudiantes = data.length;
      this.totalInscritos = data.filter(e => e.inscrito).length;
    });
    this.api.getAll('carreras').subscribe(data => {
      this.totalCarreras = data.length;
    });
    this.api.getAll('justificaciones').subscribe(data => {
      this.totalPendientes = data.filter((j: any) => j.estado.nombre === 'PRESENTADA').length;
    });
    this.api.getAll(`notificaciones/usuario/${this.usuario.id}/no-leidas`).subscribe(data => {
      this.notificaciones = data;
    });
  }

  marcarLeida(id: number) {
    this.api.put(`notificaciones/${id}/leer`, '', {}).subscribe(() => {
      this.cargarDatos();
    });
  }

  cerrarSesion() {
    sessionStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }
}