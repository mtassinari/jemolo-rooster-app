import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { LinguaDetailComponent } from 'app/entities/lingua/lingua-detail.component';
import { Lingua } from 'app/shared/model/lingua.model';

describe('Component Tests', () => {
  describe('Lingua Management Detail Component', () => {
    let comp: LinguaDetailComponent;
    let fixture: ComponentFixture<LinguaDetailComponent>;
    const route = ({ data: of({ lingua: new Lingua(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [LinguaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LinguaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LinguaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lingua on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lingua).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
