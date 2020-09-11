import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { DichiarazioniUpdateComponent } from 'app/entities/dichiarazioni/dichiarazioni-update.component';
import { DichiarazioniService } from 'app/entities/dichiarazioni/dichiarazioni.service';
import { Dichiarazioni } from 'app/shared/model/dichiarazioni.model';

describe('Component Tests', () => {
  describe('Dichiarazioni Management Update Component', () => {
    let comp: DichiarazioniUpdateComponent;
    let fixture: ComponentFixture<DichiarazioniUpdateComponent>;
    let service: DichiarazioniService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [DichiarazioniUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DichiarazioniUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DichiarazioniUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DichiarazioniService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Dichiarazioni(123);
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
        const entity = new Dichiarazioni();
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
