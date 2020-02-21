import { RigaOrdine } from './riga-ordine';
import { Ordine } from './ordine';

export class ViewCarrelloResponseDto {
  carrello: RigaOrdine[];
  ordine: Ordine;
  totale: number;
}
