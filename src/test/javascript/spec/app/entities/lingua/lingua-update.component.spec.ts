import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { LinguaUpdateComponent } from 'app/entities/lingua/lingua-update.component';
import { LinguaService } from 'app/entities/lingua/lingua.service';
import { Lingua } from 'app/shared/model/lingua.model';

describe('Component Tests', () => {
  describe('Lingua Management Update Component', () => {
    let comp: LinguaUpdateComponent;
    let fixture: ComponentFixture<LinguaUpdateComponent>;
    let service: LinguaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [LinguaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LinguaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LinguaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LinguaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Lingua(123);
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
        const entity = new Lingua();
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
