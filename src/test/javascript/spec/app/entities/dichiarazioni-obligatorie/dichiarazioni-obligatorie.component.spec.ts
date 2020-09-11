import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { DichiarazioniObligatorieComponent } from 'app/entities/dichiarazioni-obligatorie/dichiarazioni-obligatorie.component';
import { DichiarazioniObligatorieService } from 'app/entities/dichiarazioni-obligatorie/dichiarazioni-obligatorie.service';
import { DichiarazioniObligatorie } from 'app/shared/model/dichiarazioni-obligatorie.model';

describe('Component Tests', () => {
  describe('DichiarazioniObligatorie Management Component', () => {
    let comp: DichiarazioniObligatorieComponent;
    let fixture: ComponentFixture<DichiarazioniObligatorieComponent>;
    let service: DichiarazioniObligatorieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [DichiarazioniObligatorieComponent]
      })
        .overrideTemplate(DichiarazioniObligatorieComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DichiarazioniObligatorieComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DichiarazioniObligatorieService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DichiarazioniObligatorie(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dichiarazioniObligatories && comp.dichiarazioniObligatories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
