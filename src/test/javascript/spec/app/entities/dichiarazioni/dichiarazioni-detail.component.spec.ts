import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { DichiarazioniDetailComponent } from 'app/entities/dichiarazioni/dichiarazioni-detail.component';
import { Dichiarazioni } from 'app/shared/model/dichiarazioni.model';

describe('Component Tests', () => {
  describe('Dichiarazioni Management Detail Component', () => {
    let comp: DichiarazioniDetailComponent;
    let fixture: ComponentFixture<DichiarazioniDetailComponent>;
    const route = ({ data: of({ dichiarazioni: new Dichiarazioni(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [DichiarazioniDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DichiarazioniDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DichiarazioniDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dichiarazioni on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dichiarazioni).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
