import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICompetenza } from 'app/shared/model/competenza.model';

type EntityResponseType = HttpResponse<ICompetenza>;
type EntityArrayResponseType = HttpResponse<ICompetenza[]>;

@Injectable({ providedIn: 'root' })
export class CompetenzaService {
  public resourceUrl = SERVER_API_URL + 'api/competenzas';

  constructor(protected http: HttpClient) {}

  create(competenza: ICompetenza): Observable<EntityResponseType> {
    return this.http.post<ICompetenza>(this.resourceUrl, competenza, { observe: 'response' });
  }

  update(competenza: ICompetenza): Observable<EntityResponseType> {
    return this.http.put<ICompetenza>(this.resourceUrl, competenza, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICompetenza>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICompetenza[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
