import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { LANGUAGES } from 'app/config/language.constants';
import { Employee } from '../employees.model';
import { EmployeesService } from '../service/employees.service';
import { IEmployee } from '../employees.model';

@Component({
  selector: 'jhi-employee-mgmt-update',
  templateUrl: './employees-update.component.html',
})
export class EmployeesUpdateComponent implements OnInit {
  employee!: Employee;
  languages = LANGUAGES;
  authorities: string[] = [];
  managers: IEmployee[] = [];
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    login: [
      '',
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$'),
      ],
    ],
    firstName: ['', [Validators.maxLength(50)]],
    lastName: ['', [Validators.maxLength(50)]],
    email: ['', [Validators.minLength(5), Validators.maxLength(254), Validators.email]],
    activated: [],
    langKey: [],
    authorities: [],
  });

  constructor(private employeeService: EmployeesService, private route: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.route.data.subscribe(( data ) => {
      if(data.user){
        this.employee = data.user;
        if (this.employee.employeeId === undefined) {
          this.employee.activated = true;
        }
        this.updateForm(this.employee);
      }
    });
    this.employeeService.authorities().subscribe(authorities => (this.authorities = authorities));
    
    this.employeeService.getAll().subscribe(managers =>(this.managers = managers));
    
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    this.updateEmployee(this.employee);
    if (this.employee.employeeId !== undefined) {
      this.employeeService.update(this.employee).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    } else {
      this.employeeService.create(this.employee).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    }
  }

  private updateForm(employee: Employee): void {
    this.editForm.patchValue({
      id: employee.employeeId,
      login: employee.login,
      firstName: employee.firstName,
      lastName: employee.lastName,
      email: employee.email,
      activated: employee.activated,
      langKey: employee.langKey,
      authorities: employee.authorities,
    });
  }

  private updateEmployee(employee: Employee): void {
    employee.login = this.editForm.get(['login'])!.value;
    employee.firstName = this.editForm.get(['firstName'])!.value;
    employee.lastName = this.editForm.get(['lastName'])!.value;
    employee.email = this.editForm.get(['email'])!.value;
    employee.activated = this.editForm.get(['activated'])!.value;
    employee.langKey = this.editForm.get(['langKey'])!.value;
    employee.authorities = this.editForm.get(['authorities'])!.value;
  }

  private onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError(): void {
    this.isSaving = false;
  }
}
