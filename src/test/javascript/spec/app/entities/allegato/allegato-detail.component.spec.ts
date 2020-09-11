import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { AllegatoDetailComponent } from 'app/entities/allegato/allegato-detail.component';
import { Allegato } from 'app/shared/model/allegato.model';

describe('Component Tests', () => {
  describe('Allegato Management Detail Component', () => {
    let comp: AllegatoDetailComponent;
    let fixture: ComponentFixture<AllegatoDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ allegato: new Allegato(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [AllegatoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AllegatoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AllegatoDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load allegato on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.allegato).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
