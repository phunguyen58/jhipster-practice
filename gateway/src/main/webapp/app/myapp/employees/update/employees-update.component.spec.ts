import { ComponentFixture, TestBed, waitForAsync, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Authority } from 'app/config/authority.constants';
import { EmployeesService } from '../service/employees.service';
import { User } from 'app/entities/user/user.model';

import { EmployeesUpdateComponent } from './employees-update.component';

describe('Component Tests', () => {
  describe('User Management Update Component', () => {
    let comp: EmployeesUpdateComponent;
    let fixture: ComponentFixture<EmployeesUpdateComponent>;
    let service: EmployeesService;

    beforeEach(
      waitForAsync(() => {
        TestBed.configureTestingModule({
          imports: [HttpClientTestingModule],
          declarations: [EmployeesUpdateComponent],
          providers: [
            FormBuilder,
            {
              provide: ActivatedRoute,
              useValue: {
                data: of({ user: new User(123, 'user', 'first', 'last', 'first@last.com', true, 'en', [Authority.USER], 'admin') }),
              },
            },
          ],
        })
          .overrideTemplate(EmployeesUpdateComponent, '')
          .compileComponents();
      })
    );

    beforeEach(() => {
      fixture = TestBed.createComponent(EmployeesUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EmployeesService);
    });

    describe('OnInit', () => {
      it('Should load authorities and language on init', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          jest.spyOn(service, 'authorities').mockReturnValue(of(['USER']));

          // WHEN
          comp.ngOnInit();

          // THEN
          expect(service.authorities).toHaveBeenCalled();
          expect(comp.authorities).toEqual(['USER']);
        })
      ));
    });

    describe('save', () => {
      it('Should call update service on save for existing user', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          const entity = new User(123);
          jest.spyOn(service, 'update').mockReturnValue(of(entity));
          comp.user = entity;
          comp.editForm.patchValue({ id: entity.id });
          // WHEN
          comp.save();
          tick(); // simulate async

          // THEN
          expect(service.update).toHaveBeenCalledWith(entity);
          expect(comp.isSaving).toEqual(false);
        })
      ));

      it('Should call create service on save for new user', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          const entity = new User();
          jest.spyOn(service, 'create').mockReturnValue(of(entity));
          comp.user = entity;
          // WHEN
          comp.save();
          tick(); // simulate async

          // THEN
          expect(service.create).toHaveBeenCalledWith(entity);
          expect(comp.isSaving).toEqual(false);
        })
      ));
    });
  });
});
