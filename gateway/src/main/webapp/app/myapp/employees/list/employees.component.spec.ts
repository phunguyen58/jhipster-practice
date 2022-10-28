jest.mock('@angular/router');
jest.mock('app/core/auth/account.service');

import { ComponentFixture, TestBed, waitForAsync, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';

import { EmployeesService } from '../service/employees.service';
import { Employee } from '../employees.model';
import { AccountService } from 'app/core/auth/account.service';

import { EmployeesComponent } from './employees.component';

describe('Component Tests', () => {
  describe('Employee Management Component', () => {
    let comp: EmployeesComponent;
    let fixture: ComponentFixture<EmployeesComponent>;
    let service: EmployeesService;
    let mockAccountService: AccountService;
    const data = of({
      defaultSort: 'employeeId,asc',
    });
    const queryParamMap = of(
      jest.requireActual('@angular/router').convertToParamMap({
        page: '1',
        size: '1',
        sort: 'id,desc',
      })
    );

    beforeEach(
      waitForAsync(() => {
        TestBed.configureTestingModule({
          imports: [HttpClientTestingModule],
          declarations: [EmployeesComponent],
          providers: [Router, { provide: ActivatedRoute, useValue: { data, queryParamMap } }, AccountService],
        })
          .overrideTemplate(EmployeesComponent, '')
          .compileComponents();
      })
    );

    beforeEach(() => {
      fixture = TestBed.createComponent(EmployeesComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EmployeesService);
      mockAccountService = TestBed.inject(AccountService);
      mockAccountService.identity = jest.fn(() => of(null));
    });

    describe('OnInit', () => {
      it('Should call load all on init', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          const headers = new HttpHeaders().append('link', 'link;link');
          jest.spyOn(service, 'query').mockReturnValue(
            of(
              new HttpResponse({
                body: [new Employee(123)],
                headers,
              })
            )
          );

          // WHEN
          comp.ngOnInit();
          tick(); // simulate async

          // THEN
          expect(service.query).toHaveBeenCalled();
          expect(comp.employees?.[0]).toEqual(expect.objectContaining({ id: 123 }));
        })
      ));
    });

    describe('setActive', () => {
      it('Should update employee and call load all', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          const headers = new HttpHeaders().append('link', 'link;link');
          const employee = new Employee(123);
          jest.spyOn(service, 'query').mockReturnValue(
            of(
              new HttpResponse({
                body: [employee],
                headers,
              })
            )
          );
          jest.spyOn(service, 'update').mockReturnValue(of(employee));

          // WHEN
          comp.setActive(employee, true);
          tick(); // simulate async

          // THEN
          expect(service.update).toHaveBeenCalledWith({ ...employee, activated: true });
          expect(service.query).toHaveBeenCalled();
          expect(comp.employees?.[0]).toEqual(expect.objectContaining({ id: 123 }));
        })
      ));
    });
  });
});
