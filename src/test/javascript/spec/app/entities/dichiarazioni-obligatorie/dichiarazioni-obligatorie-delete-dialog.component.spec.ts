import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { DichiarazioniObligatorieDeleteDialogComponent } from 'app/entities/dichiarazioni-obligatorie/dichiarazioni-obligatorie-delete-dialog.component';
import { DichiarazioniObligatorieService } from 'app/entities/dichiarazioni-obligatorie/dichiarazioni-obligatorie.service';

describe('Component Tests', () => {
  describe('DichiarazioniObligatorie Management Delete Component', () => {
    let comp: DichiarazioniObligatorieDeleteDialogComponent;
    let fixture: ComponentFixture<DichiarazioniObligatorieDeleteDialogComponent>;
    let service: DichiarazioniObligatorieService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [DichiarazioniObligatorieDeleteDialogComponent]
      })
        .overrideTemplate(DichiarazioniObligatorieDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DichiarazioniObligatorieDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DichiarazioniObligatorieService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
