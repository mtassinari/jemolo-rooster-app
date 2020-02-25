import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { AmbitoCompetenzaUpdateComponent } from 'app/entities/ambito-competenza/ambito-competenza-update.component';
import { AmbitoCompetenzaService } from 'app/entities/ambito-competenza/ambito-competenza.service';
import { AmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';

describe('Component Tests', () => {
  describe('AmbitoCompetenza Management Update Component', () => {
    let comp: AmbitoCompetenzaUpdateComponent;
    let fixture: ComponentFixture<AmbitoCompetenzaUpdateComponent>;
    let service: AmbitoCompetenzaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [AmbitoCompetenzaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AmbitoCompetenzaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AmbitoCompetenzaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AmbitoCompetenzaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AmbitoCompetenza(123);
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
        const entity = new AmbitoCompetenza();
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
