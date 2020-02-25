import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';

type EntityResponseType = HttpResponse<IAmbitoCompetenza>;
type EntityArrayResponseType = HttpResponse<IAmbitoCompetenza[]>;

@Injectable({ providedIn: 'root' })
export class AmbitoCompetenzaService {
  public resourceUrl = SERVER_API_URL + 'api/ambito-competenzas';

  constructor(protected http: HttpClient) {}

  create(ambitoCompetenza: IAmbitoCompetenza): Observable<EntityResponseType> {
    return this.http.post<IAmbitoCompetenza>(this.resourceUrl, ambitoCompetenza, { observe: 'response' });
  }

  update(ambitoCompetenza: IAmbitoCompetenza): Observable<EntityResponseType> {
    return this.http.put<IAmbitoCompetenza>(this.resourceUrl, ambitoCompetenza, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAmbitoCompetenza>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAmbitoCompetenza[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
