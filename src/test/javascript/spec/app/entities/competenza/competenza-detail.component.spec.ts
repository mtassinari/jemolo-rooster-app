import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { CompetenzaDetailComponent } from 'app/entities/competenza/competenza-detail.component';
import { Competenza } from 'app/shared/model/competenza.model';

describe('Component Tests', () => {
  describe('Competenza Management Detail Component', () => {
    let comp: CompetenzaDetailComponent;
    let fixture: ComponentFixture<CompetenzaDetailComponent>;
    const route = ({ data: of({ competenza: new Competenza(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [CompetenzaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CompetenzaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CompetenzaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load competenza on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.competenza).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
