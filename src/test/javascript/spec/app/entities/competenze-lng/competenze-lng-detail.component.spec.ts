import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { CompetenzeLngDetailComponent } from 'app/entities/competenze-lng/competenze-lng-detail.component';
import { CompetenzeLng } from 'app/shared/model/competenze-lng.model';

describe('Component Tests', () => {
  describe('CompetenzeLng Management Detail Component', () => {
    let comp: CompetenzeLngDetailComponent;
    let fixture: ComponentFixture<CompetenzeLngDetailComponent>;
    const route = ({ data: of({ competenzeLng: new CompetenzeLng(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [CompetenzeLngDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CompetenzeLngDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CompetenzeLngDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load competenzeLng on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.competenzeLng).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
