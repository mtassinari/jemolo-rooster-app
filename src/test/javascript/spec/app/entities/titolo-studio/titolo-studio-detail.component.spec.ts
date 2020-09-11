import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { TitoloStudioDetailComponent } from 'app/entities/titolo-studio/titolo-studio-detail.component';
import { TitoloStudio } from 'app/shared/model/titolo-studio.model';

describe('Component Tests', () => {
  describe('TitoloStudio Management Detail Component', () => {
    let comp: TitoloStudioDetailComponent;
    let fixture: ComponentFixture<TitoloStudioDetailComponent>;
    const route = ({ data: of({ titoloStudio: new TitoloStudio(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [TitoloStudioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TitoloStudioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TitoloStudioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load titoloStudio on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.titoloStudio).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
