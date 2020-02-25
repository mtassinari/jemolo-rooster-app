import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { TitoloStudioUpdateComponent } from 'app/entities/titolo-studio/titolo-studio-update.component';
import { TitoloStudioService } from 'app/entities/titolo-studio/titolo-studio.service';
import { TitoloStudio } from 'app/shared/model/titolo-studio.model';

describe('Component Tests', () => {
  describe('TitoloStudio Management Update Component', () => {
    let comp: TitoloStudioUpdateComponent;
    let fixture: ComponentFixture<TitoloStudioUpdateComponent>;
    let service: TitoloStudioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [TitoloStudioUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TitoloStudioUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TitoloStudioUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TitoloStudioService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TitoloStudio(123);
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
        const entity = new TitoloStudio();
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
