import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AnagraficaCandidatoService } from 'app/entities/anagrafica-candidato/anagrafica-candidato.service';
import { IAnagraficaCandidato, AnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';

describe('Service Tests', () => {
  describe('AnagraficaCandidato Service', () => {
    let injector: TestBed;
    let service: AnagraficaCandidatoService;
    let httpMock: HttpTestingController;
    let elemDefault: IAnagraficaCandidato;
    let expectedResult: IAnagraficaCandidato | IAnagraficaCandidato[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AnagraficaCandidatoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AnagraficaCandidato(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataNascita: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AnagraficaCandidato', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataNascita: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascita: currentDate,
          },
          returnedFromService
        );

        service.create(new AnagraficaCandidato()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AnagraficaCandidato', () => {
        const returnedFromService = Object.assign(
          {
            cognome: 'BBBBBB',
            nome: 'BBBBBB',
            luogoNascita: 'BBBBBB',
            dataNascita: currentDate.format(DATE_FORMAT),
            codiceFiscale: 'BBBBBB',
            professione: 'BBBBBB',
            partitaIva: 'BBBBBB',
            numeroTelefonoFisso: 'BBBBBB',
            numeroTelefonoCellulare: 'BBBBBB',
            eMail: 'BBBBBB',
            indirizzoPec: 'BBBBBB',
            indirizzoResidenza: 'BBBBBB',
            capResidenza: 'BBBBBB',
            comuneResidenza: 'BBBBBB',
            provinciaResidenza: 'BBBBBB',
            note: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascita: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AnagraficaCandidato', () => {
        const returnedFromService = Object.assign(
          {
            cognome: 'BBBBBB',
            nome: 'BBBBBB',
            luogoNascita: 'BBBBBB',
            dataNascita: currentDate.format(DATE_FORMAT),
            codiceFiscale: 'BBBBBB',
            professione: 'BBBBBB',
            partitaIva: 'BBBBBB',
            numeroTelefonoFisso: 'BBBBBB',
            numeroTelefonoCellulare: 'BBBBBB',
            eMail: 'BBBBBB',
            indirizzoPec: 'BBBBBB',
            indirizzoResidenza: 'BBBBBB',
            capResidenza: 'BBBBBB',
            comuneResidenza: 'BBBBBB',
            provinciaResidenza: 'BBBBBB',
            note: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascita: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AnagraficaCandidato', () => {
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
