import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AnagraficaCandidatoService } from './anagrafica-candidato.service';
import { AnagraficaCandidatoDeleteDialogComponent } from './anagrafica-candidato-delete-dialog.component';

@Component({
  selector: 'jhi-anagrafica-candidato',
  templateUrl: './anagrafica-candidato.component.html'
})
export class AnagraficaCandidatoComponent implements OnInit, OnDestroy {
  anagraficaCandidatoes?: IAnagraficaCandidato[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected anagraficaCandidatoService: AnagraficaCandidatoService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.anagraficaCandidatoService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IAnagraficaCandidato[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInAnagraficaCandidatoes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAnagraficaCandidato): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAnagraficaCandidatoes(): void {
    this.eventSubscriber = this.eventManager.subscribe('anagraficaCandidatoListModification', () => this.loadPage());
  }

  delete(anagraficaCandidato: IAnagraficaCandidato): void {
    const modalRef = this.modalService.open(AnagraficaCandidatoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.anagraficaCandidato = anagraficaCandidato;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IAnagraficaCandidato[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/anagrafica-candidato'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.anagraficaCandidatoes = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
