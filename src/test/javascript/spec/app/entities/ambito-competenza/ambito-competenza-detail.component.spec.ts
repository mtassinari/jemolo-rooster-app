import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { AmbitoCompetenzaDetailComponent } from 'app/entities/ambito-competenza/ambito-competenza-detail.component';
import { AmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';

describe('Component Tests', () => {
  describe('AmbitoCompetenza Management Detail Component', () => {
    let comp: AmbitoCompetenzaDetailComponent;
    let fixture: ComponentFixture<AmbitoCompetenzaDetailComponent>;
    const route = ({ data: of({ ambitoCompetenza: new AmbitoCompetenza(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [AmbitoCompetenzaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AmbitoCompetenzaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AmbitoCompetenzaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ambitoCompetenza on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ambitoCompetenza).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
