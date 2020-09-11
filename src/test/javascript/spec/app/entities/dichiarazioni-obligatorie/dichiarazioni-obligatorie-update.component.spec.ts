import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { DichiarazioniObligatorieUpdateComponent } from 'app/entities/dichiarazioni-obligatorie/dichiarazioni-obligatorie-update.component';
import { DichiarazioniObligatorieService } from 'app/entities/dichiarazioni-obligatorie/dichiarazioni-obligatorie.service';
import { DichiarazioniObligatorie } from 'app/shared/model/dichiarazioni-obligatorie.model';

describe('Component Tests', () => {
  describe('DichiarazioniObligatorie Management Update Component', () => {
    let comp: DichiarazioniObligatorieUpdateComponent;
    let fixture: ComponentFixture<DichiarazioniObligatorieUpdateComponent>;
    let service: DichiarazioniObligatorieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [DichiarazioniObligatorieUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DichiarazioniObligatorieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DichiarazioniObligatorieUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DichiarazioniObligatorieService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DichiarazioniObligatorie(123);
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
        const entity = new DichiarazioniObligatorie();
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
