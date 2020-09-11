import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { StatoRegistrazioneComponent } from 'app/entities/stato-registrazione/stato-registrazione.component';
import { StatoRegistrazioneService } from 'app/entities/stato-registrazione/stato-registrazione.service';
import { StatoRegistrazione } from 'app/shared/model/stato-registrazione.model';

describe('Component Tests', () => {
  describe('StatoRegistrazione Management Component', () => {
    let comp: StatoRegistrazioneComponent;
    let fixture: ComponentFixture<StatoRegistrazioneComponent>;
    let service: StatoRegistrazioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [StatoRegistrazioneComponent],
      })
        .overrideTemplate(StatoRegistrazioneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StatoRegistrazioneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatoRegistrazioneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new StatoRegistrazione(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.statoRegistraziones && comp.statoRegistraziones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
