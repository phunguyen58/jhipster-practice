jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, waitForAsync, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';

import { EmployeesService } from '../service/employees.service';

import { EmployeesDeleteDialogComponent } from './employees-delete-dialog.component';

describe('Component Tests', () => {
  describe('User Management Delete Component', () => {
    let comp: EmployeesDeleteDialogComponent;
    let fixture: ComponentFixture<EmployeesDeleteDialogComponent>;
    let service: EmployeesService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(
      waitForAsync(() => {
        TestBed.configureTestingModule({
          imports: [HttpClientTestingModule],
          declarations: [EmployeesDeleteDialogComponent],
          providers: [NgbActiveModal],
        })
          .overrideTemplate(EmployeesDeleteDialogComponent, '')
          .compileComponents();
      })
    );

    beforeEach(() => {
      fixture = TestBed.createComponent(EmployeesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EmployeesService);
      mockActiveModal = TestBed.inject(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          jest.spyOn(service, 'delete').mockReturnValue(of({}));

          // WHEN
          comp.confirmDelete('user');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('user');
          expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
        })
      ));
    });
  });
});
