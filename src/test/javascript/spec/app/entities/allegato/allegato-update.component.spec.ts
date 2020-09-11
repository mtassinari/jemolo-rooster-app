import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { AllegatoUpdateComponent } from 'app/entities/allegato/allegato-update.component';
import { AllegatoService } from 'app/entities/allegato/allegato.service';
import { Allegato } from 'app/shared/model/allegato.model';

describe('Component Tests', () => {
  describe('Allegato Management Update Component', () => {
    let comp: AllegatoUpdateComponent;
    let fixture: ComponentFixture<AllegatoUpdateComponent>;
    let service: AllegatoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [AllegatoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AllegatoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AllegatoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AllegatoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Allegato(123);
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
        const entity = new Allegato();
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
