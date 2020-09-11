import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { AllegatoComponent } from 'app/entities/allegato/allegato.component';
import { AllegatoService } from 'app/entities/allegato/allegato.service';
import { Allegato } from 'app/shared/model/allegato.model';

describe('Component Tests', () => {
  describe('Allegato Management Component', () => {
    let comp: AllegatoComponent;
    let fixture: ComponentFixture<AllegatoComponent>;
    let service: AllegatoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [AllegatoComponent],
      })
        .overrideTemplate(AllegatoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AllegatoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AllegatoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Allegato(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.allegatoes && comp.allegatoes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
