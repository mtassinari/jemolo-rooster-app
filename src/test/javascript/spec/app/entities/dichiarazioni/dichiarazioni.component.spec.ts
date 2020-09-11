import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { DichiarazioniComponent } from 'app/entities/dichiarazioni/dichiarazioni.component';
import { DichiarazioniService } from 'app/entities/dichiarazioni/dichiarazioni.service';
import { Dichiarazioni } from 'app/shared/model/dichiarazioni.model';

describe('Component Tests', () => {
  describe('Dichiarazioni Management Component', () => {
    let comp: DichiarazioniComponent;
    let fixture: ComponentFixture<DichiarazioniComponent>;
    let service: DichiarazioniService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [DichiarazioniComponent]
      })
        .overrideTemplate(DichiarazioniComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DichiarazioniComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DichiarazioniService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Dichiarazioni(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dichiarazionis && comp.dichiarazionis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
