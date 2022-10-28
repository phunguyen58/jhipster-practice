import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';
import { EmployeesComponent } from './list/employees.component';
import { EmployeesDetailComponent } from './detail/employees-detail.component';
import { EmployeesUpdateComponent } from './update/employees-update.component';
import { EmployeesDeleteDialogComponent } from './delete/employees-delete-dialog.component';
import { EmployeesRoute } from './employees.route';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(EmployeesRoute)],
  declarations: [
    EmployeesComponent,
    EmployeesDetailComponent,
    EmployeesUpdateComponent,
    EmployeesDeleteDialogComponent
  ],
  entryComponents: [EmployeesDeleteDialogComponent],
})
export class EmployeesModule {}
