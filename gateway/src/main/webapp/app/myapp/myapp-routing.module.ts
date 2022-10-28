import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

@NgModule({
  imports: [
    /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    RouterModule.forChild([
      {
        path: 'employees',
        loadChildren: () => import('./employees/employees.module').then(m => m.EmployeesModule),
        data: {
          pageTitle: 'employees.home.title',
        },
      },
    ]),
  ],
})
export class MyAppRoutingModule {}
