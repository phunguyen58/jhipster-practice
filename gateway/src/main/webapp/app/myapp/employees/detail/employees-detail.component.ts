import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Employee } from '../employees.model';

@Component({
  selector: 'jhi-employee-mgmt-detail',
  templateUrl: './employees-detail.component.html',
})
export class EmployeesDetailComponent implements OnInit {
  employee: Employee | null = null;
  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.data.subscribe((data) => {

      this.employee = data.user;
    });
  }
}
