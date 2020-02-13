import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Visualizzatore06Component } from './visualizzatore06.component';

describe('Visualizzatore06Component', () => {
  let component: Visualizzatore06Component;
  let fixture: ComponentFixture<Visualizzatore06Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Visualizzatore06Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Visualizzatore06Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
