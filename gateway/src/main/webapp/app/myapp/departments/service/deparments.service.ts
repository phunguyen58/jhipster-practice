import { Observable } from 'rxjs';
import { ApplicationConfigService } from './../../../core/config/application-config.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Department } from './../departments.model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DeparmentsService {

  constructor() { }

  // private resourceUrl = this.ApplicationConfigServicefigService.getEndpointFor('api/employees', "employee");

  // getAll(): Observable<Department[]>{
  //   return this.http.get
  // }
}
