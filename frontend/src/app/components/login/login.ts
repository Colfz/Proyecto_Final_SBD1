import { Component, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  nombre = '';
  contrasenia = '';
  error = '';

  constructor(
    private api: ApiService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
    if (sessionStorage.getItem('usuario')) {
      this.router.navigate(['/dashboard']);
    }
  }

  login() {
    if (!this.nombre || !this.contrasenia) {
      this.error = 'Por favor completa todos los campos.';
      this.cdr.detectChanges();
      return;
    }
    this.api.login(this.nombre, this.contrasenia).subscribe({
      next: (usuario) => {
        sessionStorage.setItem('usuario', JSON.stringify(usuario));
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.error = 'Usuario o contraseña incorrectos.';
        this.cdr.detectChanges();
      }
    });
  }
}