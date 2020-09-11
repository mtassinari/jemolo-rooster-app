import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AmbitoCompetenzaService } from 'app/entities/ambito-competenza/ambito-competenza.service';
import { IAmbitoCompetenza, AmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';

describe('Service Tests', () => {
  describe('AmbitoCompetenza Service', () => {
    let injector: TestBed;
    let service: AmbitoCompetenzaService;
    let httpMock: HttpTestingController;
    let elemDefault: IAmbitoCompetenza;
    let expectedResult: IAmbitoCompetenza | IAmbitoCompetenza[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AmbitoCompetenzaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AmbitoCompetenza(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AmbitoCompetenza', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AmbitoCompetenza()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AmbitoCompetenza', () => {
        const returnedFromService = Object.assign(
          {
            descrizione: 'BBBBBB',
            tipo: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AmbitoCompetenza', () => {
        const returnedFromService = Object.assign(
          {
            descrizione: 'BBBBBB',
            tipo: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AmbitoCompetenza', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
