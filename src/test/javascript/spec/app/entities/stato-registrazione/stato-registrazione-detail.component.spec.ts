import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { StatoRegistrazioneDetailComponent } from 'app/entities/stato-registrazione/stato-registrazione-detail.component';
import { StatoRegistrazione } from 'app/shared/model/stato-registrazione.model';

describe('Component Tests', () => {
  describe('StatoRegistrazione Management Detail Component', () => {
    let comp: StatoRegistrazioneDetailComponent;
    let fixture: ComponentFixture<StatoRegistrazioneDetailComponent>;
    const route = ({ data: of({ statoRegistrazione: new StatoRegistrazione(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [StatoRegistrazioneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StatoRegistrazioneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StatoRegistrazioneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load statoRegistrazione on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.statoRegistrazione).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
