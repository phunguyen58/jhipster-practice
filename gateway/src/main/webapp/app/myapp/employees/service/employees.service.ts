import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Pagination } from 'app/core/request/request.model';
import { IEmployee } from '../employees.model';

@Injectable({ providedIn: 'root' })
export class EmployeesService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/employees', "employee");

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getAll(): Observable<IEmployee[]> {
    return this.http.get<IEmployee[]>(this.resourceUrl);
  }

  create(user: IEmployee): Observable<IEmployee> {
    return this.http.post<IEmployee>(`${this.resourceUrl}`, user);
  }

  update(user: IEmployee): Observable<IEmployee> {
    return this.http.put<IEmployee>(`${this.resourceUrl}/${String(user.employeeId)}`, user);
  }

  find(login: string): Observable<IEmployee> {
    return this.http.get<IEmployee>(`${this.resourceUrl}/${login}`);
  }

  query(req?: Pagination): Observable<HttpResponse<IEmployee[]>> {
    const options = createRequestOption(req);
    return this.http.get<IEmployee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(employeeId: number): Observable<{}> {
    return this.http.delete(`${this.resourceUrl}/${String(employeeId)}`);
  }

  authorities(): Observable<string[]> {
    return this.http.get<string[]>(this.applicationConfigService.getEndpointFor('api/authorities'));
  }
}
