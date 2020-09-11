import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { DichiarazioniObligatorieDetailComponent } from 'app/entities/dichiarazioni-obligatorie/dichiarazioni-obligatorie-detail.component';
import { DichiarazioniObligatorie } from 'app/shared/model/dichiarazioni-obligatorie.model';

describe('Component Tests', () => {
  describe('DichiarazioniObligatorie Management Detail Component', () => {
    let comp: DichiarazioniObligatorieDetailComponent;
    let fixture: ComponentFixture<DichiarazioniObligatorieDetailComponent>;
    const route = ({ data: of({ dichiarazioniObligatorie: new DichiarazioniObligatorie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [DichiarazioniObligatorieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DichiarazioniObligatorieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DichiarazioniObligatorieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dichiarazioniObligatorie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dichiarazioniObligatorie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
