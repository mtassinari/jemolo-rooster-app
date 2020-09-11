import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JemoloRoosterAppSharedModule } from 'app/shared/shared.module';
import { TitoloStudioComponent } from './titolo-studio.component';
import { TitoloStudioDetailComponent } from './titolo-studio-detail.component';
import { TitoloStudioUpdateComponent } from './titolo-studio-update.component';
import { TitoloStudioDeleteDialogComponent } from './titolo-studio-delete-dialog.component';
import { titoloStudioRoute } from './titolo-studio.route';

@NgModule({
  imports: [JemoloRoosterAppSharedModule, RouterModule.forChild(titoloStudioRoute)],
  declarations: [TitoloStudioComponent, TitoloStudioDetailComponent, TitoloStudioUpdateComponent, TitoloStudioDeleteDialogComponent],
  entryComponents: [TitoloStudioDeleteDialogComponent]
})
export class JemoloRoosterAppTitoloStudioModule {}
