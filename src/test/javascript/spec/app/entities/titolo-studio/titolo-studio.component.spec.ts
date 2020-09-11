import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { TitoloStudioComponent } from 'app/entities/titolo-studio/titolo-studio.component';
import { TitoloStudioService } from 'app/entities/titolo-studio/titolo-studio.service';
import { TitoloStudio } from 'app/shared/model/titolo-studio.model';

describe('Component Tests', () => {
  describe('TitoloStudio Management Component', () => {
    let comp: TitoloStudioComponent;
    let fixture: ComponentFixture<TitoloStudioComponent>;
    let service: TitoloStudioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [TitoloStudioComponent],
      })
        .overrideTemplate(TitoloStudioComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TitoloStudioComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TitoloStudioService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TitoloStudio(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.titoloStudios && comp.titoloStudios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
