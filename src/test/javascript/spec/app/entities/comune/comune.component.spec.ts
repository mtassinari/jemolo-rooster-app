import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { ComuneComponent } from 'app/entities/comune/comune.component';
import { ComuneService } from 'app/entities/comune/comune.service';
import { Comune } from 'app/shared/model/comune.model';

describe('Component Tests', () => {
  describe('Comune Management Component', () => {
    let comp: ComuneComponent;
    let fixture: ComponentFixture<ComuneComponent>;
    let service: ComuneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [ComuneComponent],
      })
        .overrideTemplate(ComuneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ComuneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ComuneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Comune(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.comunes && comp.comunes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
