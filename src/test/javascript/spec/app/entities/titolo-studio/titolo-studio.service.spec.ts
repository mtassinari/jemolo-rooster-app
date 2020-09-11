import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TitoloStudioService } from 'app/entities/titolo-studio/titolo-studio.service';
import { ITitoloStudio, TitoloStudio } from 'app/shared/model/titolo-studio.model';

describe('Service Tests', () => {
  describe('TitoloStudio Service', () => {
    let injector: TestBed;
    let service: TitoloStudioService;
    let httpMock: HttpTestingController;
    let elemDefault: ITitoloStudio;
    let expectedResult: ITitoloStudio | ITitoloStudio[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TitoloStudioService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TitoloStudio(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TitoloStudio', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TitoloStudio()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TitoloStudio', () => {
        const returnedFromService = Object.assign(
          {
            tipologia: 'BBBBBB',
            descrizione: 'BBBBBB',
            conseguimento: 'BBBBBB',
            anno: 'BBBBBB',
            voto: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TitoloStudio', () => {
        const returnedFromService = Object.assign(
          {
            tipologia: 'BBBBBB',
            descrizione: 'BBBBBB',
            conseguimento: 'BBBBBB',
            anno: 'BBBBBB',
            voto: 'BBBBBB',
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

      it('should delete a TitoloStudio', () => {
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
