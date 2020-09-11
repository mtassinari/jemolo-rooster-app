import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DichiarazioniObligatorieService } from 'app/entities/dichiarazioni-obligatorie/dichiarazioni-obligatorie.service';
import { IDichiarazioniObligatorie, DichiarazioniObligatorie } from 'app/shared/model/dichiarazioni-obligatorie.model';

describe('Service Tests', () => {
  describe('DichiarazioniObligatorie Service', () => {
    let injector: TestBed;
    let service: DichiarazioniObligatorieService;
    let httpMock: HttpTestingController;
    let elemDefault: IDichiarazioniObligatorie;
    let expectedResult: IDichiarazioniObligatorie | IDichiarazioniObligatorie[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DichiarazioniObligatorieService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new DichiarazioniObligatorie(0, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DichiarazioniObligatorie', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new DichiarazioniObligatorie()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DichiarazioniObligatorie', () => {
        const returnedFromService = Object.assign(
          {
            stato: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DichiarazioniObligatorie', () => {
        const returnedFromService = Object.assign(
          {
            stato: true,
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

      it('should delete a DichiarazioniObligatorie', () => {
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
