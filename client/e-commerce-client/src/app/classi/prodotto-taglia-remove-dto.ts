import { LoginResponseDto } from './login-response-dto';
import { Taglia } from './taglia';
import { Prodotto } from './prodotto';

export class ProdottoTagliaRemoveDto extends LoginResponseDto {
  taglia: Taglia;
  prodotto: Prodotto;
}
