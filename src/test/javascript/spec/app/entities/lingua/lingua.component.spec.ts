import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JemoloRoosterAppTestModule } from '../../../test.module';
import { LinguaComponent } from 'app/entities/lingua/lingua.component';
import { LinguaService } from 'app/entities/lingua/lingua.service';
import { Lingua } from 'app/shared/model/lingua.model';

describe('Component Tests', () => {
  describe('Lingua Management Component', () => {
    let comp: LinguaComponent;
    let fixture: ComponentFixture<LinguaComponent>;
    let service: LinguaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JemoloRoosterAppTestModule],
        declarations: [LinguaComponent],
      })
        .overrideTemplate(LinguaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LinguaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LinguaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Lingua(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.linguas && comp.linguas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
