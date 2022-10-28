import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Authority } from 'app/config/authority.constants';
import { User } from '../employees.model';

import { EmployeesDetailComponent } from './employees-detail.component';

describe('Component Tests', () => {
  describe('User Management Detail Component', () => {
    let comp: EmployeesDetailComponent;
    let fixture: ComponentFixture<EmployeesDetailComponent>;

    beforeEach(
      waitForAsync(() => {
        TestBed.configureTestingModule({
          declarations: [EmployeesDetailComponent],
          providers: [
            {
              provide: ActivatedRoute,
              useValue: {
                data: of({ user: new User(123, 'user', 'first', 'last', 'first@last.com', true, 'en', [Authority.USER], 'admin') }),
              },
            },
          ],
        })
          .overrideTemplate(EmployeesDetailComponent, '')
          .compileComponents();
      })
    );

    beforeEach(() => {
      fixture = TestBed.createComponent(EmployeesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.user).toEqual(
          expect.objectContaining({
            id: 123,
            login: 'user',
            firstName: 'first',
            lastName: 'last',
            email: 'first@last.com',
            activated: true,
            langKey: 'en',
            authorities: [Authority.USER],
            createdBy: 'admin',
          })
        );
      });
    });
  });
});
