import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { ProvinciaDetailComponent } from 'app/entities/provincia/provincia-detail.component';
import { Provincia } from 'app/shared/model/provincia.model';

describe('Component Tests', () => {
  describe('Provincia Management Detail Component', () => {
    let comp: ProvinciaDetailComponent;
    let fixture: ComponentFixture<ProvinciaDetailComponent>;
    const route = ({ data: of({ provincia: new Provincia(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [ProvinciaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProvinciaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProvinciaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load provincia on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.provincia).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
