import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { AmbitoCompetenzaComponent } from 'app/entities/ambito-competenza/ambito-competenza.component';
import { AmbitoCompetenzaService } from 'app/entities/ambito-competenza/ambito-competenza.service';
import { AmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';

describe('Component Tests', () => {
  describe('AmbitoCompetenza Management Component', () => {
    let comp: AmbitoCompetenzaComponent;
    let fixture: ComponentFixture<AmbitoCompetenzaComponent>;
    let service: AmbitoCompetenzaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [AmbitoCompetenzaComponent],
      })
        .overrideTemplate(AmbitoCompetenzaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AmbitoCompetenzaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AmbitoCompetenzaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AmbitoCompetenza(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ambitoCompetenzas && comp.ambitoCompetenzas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
