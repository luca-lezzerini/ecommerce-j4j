import { Colori } from './colori';
import { TagliaColoriRequestDto } from './taglia-colori-request-dto';
export class TagliaColoriUpdateDto extends TagliaColoriRequestDto {
  coloreSelezionato: Colori;
}
