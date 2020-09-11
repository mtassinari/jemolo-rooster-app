import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { CompetenzeLngUpdateComponent } from 'app/entities/competenze-lng/competenze-lng-update.component';
import { CompetenzeLngService } from 'app/entities/competenze-lng/competenze-lng.service';
import { CompetenzeLng } from 'app/shared/model/competenze-lng.model';

describe('Component Tests', () => {
  describe('CompetenzeLng Management Update Component', () => {
    let comp: CompetenzeLngUpdateComponent;
    let fixture: ComponentFixture<CompetenzeLngUpdateComponent>;
    let service: CompetenzeLngService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [CompetenzeLngUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CompetenzeLngUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompetenzeLngUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompetenzeLngService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CompetenzeLng(123);
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
        const entity = new CompetenzeLng();
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
