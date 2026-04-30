import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'login',
    loadComponent: () => import('./components/login/login').then(m => m.Login)
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./components/dashboard/dashboard').then(m => m.Dashboard)
  },
  {
    path: 'estudiantes',
    loadComponent: () => import('./components/estudiantes/estudiantes').then(m => m.Estudiantes)
  },
  {
    path: 'justificaciones',
    loadComponent: () => import('./components/justificaciones/justificaciones').then(m => m.Justificaciones)
  },
  {
    path: 'catalogos',
    loadComponent: () => import('./components/catalogos/catalogos').then(m => m.Catalogos)
  }
];