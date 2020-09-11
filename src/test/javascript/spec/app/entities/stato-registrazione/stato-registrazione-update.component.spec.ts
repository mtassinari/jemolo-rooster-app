import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { StatoRegistrazioneUpdateComponent } from 'app/entities/stato-registrazione/stato-registrazione-update.component';
import { StatoRegistrazioneService } from 'app/entities/stato-registrazione/stato-registrazione.service';
import { StatoRegistrazione } from 'app/shared/model/stato-registrazione.model';

describe('Component Tests', () => {
  describe('StatoRegistrazione Management Update Component', () => {
    let comp: StatoRegistrazioneUpdateComponent;
    let fixture: ComponentFixture<StatoRegistrazioneUpdateComponent>;
    let service: StatoRegistrazioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [StatoRegistrazioneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StatoRegistrazioneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StatoRegistrazioneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatoRegistrazioneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StatoRegistrazione(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new StatoRegistrazione();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
